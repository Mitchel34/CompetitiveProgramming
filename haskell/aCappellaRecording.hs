import Data.List (sort)

main :: IO ()
main = do
    input <- getContents
    let (_:d:ps) = map read (words input) :: [Int]
        pitches   = sort ps
    print $ recordingsNeeded d pitches

-- | recordingsNeeded d xs = minimum number of intervals of length d
--   needed to cover all points in the sorted list xs.
recordingsNeeded :: Int   -- ^ maximum allowed pitch-range per recording
                 -> [Int] -- ^ sorted list of pitches
                 -> Int
recordingsNeeded _ []     = 0
recordingsNeeded d (x:xs) =
    1 + recordingsNeeded d (dropWhile (<= x + d) xs)

