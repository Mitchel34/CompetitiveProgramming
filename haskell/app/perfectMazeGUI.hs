-- Use gloss for rendering: cabal install gloss
import Graphics.Gloss
import System.Random (randomRIO)
import Data.Array
import Data.List (minimumBy, foldl')
import Data.Ord (comparing)
import qualified Data.Set as Set
import Control.Monad (foldM, when, forM_)
import Data.Maybe (catMaybes)
import qualified Data.Map as Map

-- Maze dimensions
mazeWidth, mazeHeight :: Int
mazeWidth = 50
mazeHeight = 50

type Cell = (Int, Int)
type Maze = Array Cell Bool -- True = open/passage, False = wall

type MazePath = [Cell]

-- A priority queue implementation for A* search
-- Since we don't have PSQueue, we'll implement a simple one
data PQueue a = PQueue [(a, Int)] deriving (Show)

singleton :: a -> Int -> PQueue a
singleton x p = PQueue [(x, p)]

minView :: Eq a => PQueue a -> Maybe (a, Int, PQueue a)
minView (PQueue []) = Nothing
minView (PQueue xs) = 
  let (item, prio) = minimumBy (comparing snd) xs
      rest = filter (/= (item, prio)) xs
  in Just (item, prio, PQueue rest)

insert :: a -> Int -> PQueue a -> PQueue a
insert x p (PQueue xs) = PQueue ((x, p):xs)

-- Main function
main :: IO ()
main = do
  -- Generate maze and find path
  maze <- generatePerfectMaze mazeWidth mazeHeight
  putStrLn "Maze generated!"
  let start = (0,0)
      goal = (mazeWidth-1, mazeHeight-1)
      path = aStar maze start goal
  putStrLn $ "Path found: " ++ show (length path) ++ " steps"

  -- Display maze using Gloss
  display
    (InWindow "Perfect Maze" (800, 800) (10, 10))
    white
    (renderMazeAndPath maze path)

-- Direction vectors for maze generation
data Direction = North | East | South | West
  deriving (Enum, Bounded, Show, Eq)

-- Get all directions
allDirections :: [Direction]
allDirections = [minBound..maxBound]

-- Convert direction to a cell offset
directionOffset :: Direction -> Cell
directionOffset North = (0, -1)
directionOffset East  = (1, 0)
directionOffset South = (0, 1)
directionOffset West  = (-1, 0)

-- Add two cells
addCells :: Cell -> Cell -> Cell
addCells (x1, y1) (x2, y2) = (x1 + x2, y1 + y2)

-- Check if a cell is within maze bounds
inBounds :: Int -> Int -> Cell -> Bool
inBounds w h (x, y) = x >= 0 && y >= 0 && x < w && y < h

-- Shuffle a list
shuffle :: [a] -> IO [a]
shuffle [] = return []
shuffle xs = do
  i <- randomRIO (0, length xs - 1)
  let (front, (x:back)) = splitAt i xs
  rest <- shuffle (front ++ back)
  return (x:rest)

-- Generate a perfect maze using recursive backtracker algorithm
generatePerfectMaze :: Int -> Int -> IO Maze
generatePerfectMaze w h = do
  -- Initialize maze with all walls
  let initialMaze = array ((0, 0), (w-1, h-1)) [((x, y), False) | x <- [0..w-1], y <- [0..h-1]]
  
  -- Start at a random cell
  startX <- randomRIO (0, w-1)
  startY <- randomRIO (0, h-1)
  let startCell = (startX, startY)
  
  -- Carve passages using recursive backtracking
  finalMaze <- carvePassages initialMaze [(startCell, [])] (Set.singleton startCell)
  
  -- Make sure start and end points are open
  return $ finalMaze // [((0, 0), True), ((w-1, h-1), True)]
  
-- Recursive backtracker for maze generation
carvePassages :: Maze -> [(Cell, [Direction])] -> Set.Set Cell -> IO Maze
carvePassages maze [] _ = return maze
carvePassages maze ((current, tried):stack) visited = do
  -- Get available directions (not tried yet)
  let availableDirs = filter (`notElem` tried) allDirections
  
  if null availableDirs
    then carvePassages maze stack visited  -- Backtrack
    else do
      -- Choose a random direction
      dirs <- shuffle availableDirs
      let dir = head dirs
      let offset = directionOffset dir
      let neighbor = addCells current offset
      
      if not (inBounds mazeWidth mazeHeight neighbor) || neighbor `Set.member` visited
        then -- Try another direction
             carvePassages maze ((current, dir:tried):stack) visited
        else do
          -- Carve passage
          let maze' = maze // [(current, True), (neighbor, True)]
          -- Add neighbor to visited
          let visited' = Set.insert neighbor visited
          -- Push new cell onto stack with empty tried list
          carvePassages maze' ((neighbor, []):((current, dir:tried)):stack) visited'

-- A* search algorithm
aStar :: Maze -> Cell -> Cell -> MazePath
aStar maze start goal =
  let
    -- Heuristic function (Manhattan distance)
    h (x1, y1) = 
      let (x2, y2) = goal
      in abs (x1 - x2) + abs (y1 - y2)
    
    -- Initial state
    openSet = singleton start (h start)  -- Changed from PQ.singleton
    cameFrom = Map.empty
    gScore = Map.singleton start 0
    fScore = Map.singleton start (h start)
    
    -- Reconstruct path from cameFrom map
    reconstructPath cameFrom current =
      case Map.lookup current cameFrom of
        Nothing -> [current]
        Just prev -> reconstructPath cameFrom prev ++ [current]
    
    -- Get neighboring cells
    getNeighbors (x, y) = 
      [ neighbor | dir <- allDirections
      , let offset = directionOffset dir
      , let neighbor = addCells (x, y) offset
      , inBounds mazeWidth mazeHeight neighbor
      , maze ! neighbor  -- Only open cells
      ]
    
    -- A* main loop
    loop openSet cameFrom gScore fScore =
      case minView openSet of  -- Changed from PQ.minView
        Nothing -> []  -- No path found
        Just (current, _, openSet')
          | current == goal -> reconstructPath cameFrom goal
          | otherwise -> 
              let 
                neighbors = getNeighbors current
                currentG = Map.findWithDefault (maxBound :: Int) current gScore
                
                -- Process each neighbor
                (newOpenSet, newCameFrom, newGScore, newFScore) = 
                  foldl' processNeighbor (openSet', cameFrom, gScore, fScore) neighbors
                
                -- Process single neighbor
                processNeighbor (os, cf, gs, fs) neighbor =
                  let tentativeG = currentG + 1  -- Cost to move is always 1
                  in if tentativeG >= Map.findWithDefault maxBound neighbor gs
                     then (os, cf, gs, fs)  -- Not a better path
                     else 
                       let 
                         newG = Map.insert neighbor tentativeG gs
                         newF = Map.insert neighbor (tentativeG + h neighbor) fs
                         newCf = Map.insert neighbor current cf
                         newOs = insert neighbor (tentativeG + h neighbor) os  -- Changed from PQ.insert
                       in (newOs, newCf, newG, newF)
              in 
                loop newOpenSet newCameFrom newGScore newFScore
  in
    loop openSet cameFrom gScore fScore

-- Render maze and path
cellSize :: Float
cellSize = 15.0

renderMazeAndPath :: Maze -> MazePath -> Picture
renderMazeAndPath maze path = 
  let 
    ((minX,minY),(maxX,maxY)) = bounds maze
    pathSet = Set.fromList path
    start = (0, 0)
    goal = (mazeWidth-1, mazeHeight-1)
    
    -- Offset to center the maze in the window
    offsetX = -(fromIntegral mazeWidth * cellSize) / 2
    offsetY = -(fromIntegral mazeHeight * cellSize) / 2
    
    -- Create a cell picture
    cellPicture x y isOpen isPath = 
      translate (offsetX + fromIntegral x * cellSize + cellSize/2)
                (offsetY + fromIntegral y * cellSize + cellSize/2) $
      if isOpen then
        if (x,y) == start then
          color green $ rectangleSolid cellSize cellSize  -- Start point
        else if (x,y) == goal then
          color blue $ rectangleSolid cellSize cellSize   -- Goal point
        else if isPath then
          color (makeColor 1 0.7 0.7 1) $ rectangleSolid cellSize cellSize  -- Path
        else
          color white $ rectangleSolid cellSize cellSize  -- Open space
      else
        color black $ rectangleSolid cellSize cellSize  -- Wall
        
    -- Create a line segment for the path
    pathLine (x1,y1) (x2,y2) =
      color red $ line [
        (offsetX + fromIntegral x1 * cellSize + cellSize/2, 
         offsetY + fromIntegral y1 * cellSize + cellSize/2),
        (offsetX + fromIntegral x2 * cellSize + cellSize/2, 
         offsetY + fromIntegral y2 * cellSize + cellSize/2)
      ]
    
    -- Generate all cell pictures
    cellPictures = 
      [ cellPicture x y (maze!(x,y)) (Set.member (x,y) pathSet) 
        | y <- [minY..maxY], x <- [minX..maxX] ]
    
    -- Generate all path lines
    pathLines = 
      if length path > 1 
      then zipWith pathLine path (tail path)
      else []
      
  in pictures $ cellPictures ++ pathLines
