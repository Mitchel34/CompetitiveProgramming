import Data.List (intercalate)

-- Define the maze as a list of strings
type Maze = [String]

-- Define the starting point
start :: (Int, Int)
start = (0, 0)

-- Define the goal point
goal :: (Int, Int)
goal = (4, 4)

-- Define the possible moves
moves :: [(Int, Int)]
moves = [(0, 1), (1, 0), (0, -1), (-1, 0)]

-- Check if a position is within the maze bounds
inBounds :: (Int, Int) -> Maze -> Bool
inBounds (x, y) maze = x >= 0 && y >= 0 && x < length maze && y < length (head maze)

-- Check if a position is a wall
isWall :: (Int, Int) -> Maze -> Bool
isWall (x, y) maze = (maze !! x !! y) == '#'

-- Find the perfect maze path using backtracking
findPath :: (Int, Int) -> (Int, Int) -> Maze -> [(Int, Int)] -> [[(Int, Int)]]
findPath current goal maze visited
  | current == goal = [reverse (current : visited)]
  | otherwise = concatMap (\move -> tryMove (addTuples current move) maze) moves
  where
    tryMove pos maze
      | inBounds pos maze && not (isWall pos maze) && not (elem pos visited) =
          findPath pos goal maze (current : visited)
      | otherwise = []

-- Helper function to add two tuples
addTuples :: (Int, Int) -> (Int, Int) -> (Int, Int)
addTuples (x1, y1) (x2, y2) = (x1 + x2, y1 + y2)

-- Main function to solve the maze
solveMaze :: Maze -> [[(Int, Int)]]
solveMaze maze = findPath start goal maze []

-- Example maze
exampleMaze :: Maze
exampleMaze = [
    ".....",
    ".###.",
    ".#.#.",
    ".###.",
    "....."
  ]

-- Print the maze
printMaze :: Maze -> IO ()
printMaze maze = putStrLn (intercalate "\n" maze)

-- Print the solution paths
printSolutions :: [[(Int, Int)]] -> IO ()
printSolutions solutions = mapM_ printSolution solutions
  where
    printSolution path = putStrLn (intercalate " -> " (map show path))

-- Main program
main :: IO ()
main = do
  putStrLn "Original Maze:"
  printMaze exampleMaze
  let solutions = solveMaze exampleMaze
  putStrLn "\nSolution Paths:"
  printSolutions solutions