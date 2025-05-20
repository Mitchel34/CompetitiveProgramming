import Control.Monad (replicateM)
import Data.Array

-- | Solve the "Acquire Hardware" problem by computing a DP table
--   where dp[i,j] = maximum number of 'I' blocks collectible
--   on a path from (0,0) to (i,j) moving only right or down.
main :: IO ()
main = do
    -- Read dimensions
    [h, w] <- map read . words <$> getLine
    -- Read grid rows
    rows <- replicateM h getLine
    -- Create an array of characters indexed by (row, column)
    let grid :: Array (Int, Int) Char
        grid = listArray ((0,0), (h-1, w-1)) (concat rows)

    -- Build DP array lazily
    let boundsDP = ((0,0), (h-1, w-1))
        dp :: Array (Int, Int) Int
        dp = array boundsDP
             [ ((i,j), best i j)
             | i <- [0..h-1], j <- [0..w-1] ]
          where
            best i j = let
                fromUp   = if i > 0 then dp ! (i-1, j) else 0
                fromLeft = if j > 0 then dp ! (i, j-1) else 0
                here     = if grid ! (i, j) == 'I' then 1 else 0
              in max fromUp fromLeft + here

    -- Output the result at bottom-right corner
    print $ dp ! (h-1, w-1)

