-- SpeedLimit.hs
-- A Haskell solution for the "Speed Limit" problem.
-- Reads multiple data sets from standard input and computes the total distance driven for each.

main :: IO ()
main = do
    contents <- getContents
    let ls = lines contents
        results = process ls
    mapM_ putStrLn results

-- Process input lines into output lines
process :: [String] -> [String]
process [] = []
process (x:xs) =
    let n = read x :: Int
    in if n == -1
       then []
       else
         let (entries, rest) = splitAt n xs
             pairs = map (toPair . words) entries
             dist = computeDistance pairs
         in (show dist ++ " miles") : process rest
  where
    toPair [s, t] = (read s, read t)
    toPair _      = error "Invalid input line"

-- Compute distance given a list of (speed, elapsedTime) pairs
-- Distance = sum over i of speed_i * (time_i - time_{i-1}), where time_0 = 0
computeDistance :: [(Int, Int)] -> Int
computeDistance ps = sum [ s * (t - prevT) | ((s, t), prevT) <- zip ps (0 : map snd ps) ]

