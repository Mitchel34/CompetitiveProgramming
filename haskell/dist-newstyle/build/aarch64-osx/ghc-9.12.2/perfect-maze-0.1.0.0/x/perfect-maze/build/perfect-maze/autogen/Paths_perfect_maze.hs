{-# LANGUAGE CPP #-}
{-# LANGUAGE NoRebindableSyntax #-}
#if __GLASGOW_HASKELL__ >= 810
{-# OPTIONS_GHC -Wno-prepositive-qualified-module #-}
#endif
{-# OPTIONS_GHC -fno-warn-missing-import-lists #-}
{-# OPTIONS_GHC -w #-}
module Paths_perfect_maze (
    version,
    getBinDir, getLibDir, getDynLibDir, getDataDir, getLibexecDir,
    getDataFileName, getSysconfDir
  ) where


import qualified Control.Exception as Exception
import qualified Data.List as List
import Data.Version (Version(..))
import System.Environment (getEnv)
import Prelude


#if defined(VERSION_base)

#if MIN_VERSION_base(4,0,0)
catchIO :: IO a -> (Exception.IOException -> IO a) -> IO a
#else
catchIO :: IO a -> (Exception.Exception -> IO a) -> IO a
#endif

#else
catchIO :: IO a -> (Exception.IOException -> IO a) -> IO a
#endif
catchIO = Exception.catch

version :: Version
version = Version [0,1,0,0] []

getDataFileName :: FilePath -> IO FilePath
getDataFileName name = do
  dir <- getDataDir
  return (dir `joinFileName` name)

getBinDir, getLibDir, getDynLibDir, getDataDir, getLibexecDir, getSysconfDir :: IO FilePath




bindir, libdir, dynlibdir, datadir, libexecdir, sysconfdir :: FilePath
bindir     = "/Users/mitchelcarson/.cabal/bin"
libdir     = "/Users/mitchelcarson/.cabal/lib/aarch64-osx-ghc-9.12.2-inplace/perfect-maze-0.1.0.0-inplace-perfect-maze"
dynlibdir  = "/Users/mitchelcarson/.cabal/lib/aarch64-osx-ghc-9.12.2-inplace"
datadir    = "/Users/mitchelcarson/.cabal/share/aarch64-osx-ghc-9.12.2-inplace/perfect-maze-0.1.0.0"
libexecdir = "/Users/mitchelcarson/.cabal/libexec/aarch64-osx-ghc-9.12.2-inplace/perfect-maze-0.1.0.0"
sysconfdir = "/Users/mitchelcarson/.cabal/etc"

getBinDir     = catchIO (getEnv "perfect_maze_bindir")     (\_ -> return bindir)
getLibDir     = catchIO (getEnv "perfect_maze_libdir")     (\_ -> return libdir)
getDynLibDir  = catchIO (getEnv "perfect_maze_dynlibdir")  (\_ -> return dynlibdir)
getDataDir    = catchIO (getEnv "perfect_maze_datadir")    (\_ -> return datadir)
getLibexecDir = catchIO (getEnv "perfect_maze_libexecdir") (\_ -> return libexecdir)
getSysconfDir = catchIO (getEnv "perfect_maze_sysconfdir") (\_ -> return sysconfdir)



joinFileName :: String -> String -> FilePath
joinFileName ""  fname = fname
joinFileName "." fname = fname
joinFileName dir ""    = dir
joinFileName dir fname
  | isPathSeparator (List.last dir) = dir ++ fname
  | otherwise                       = dir ++ pathSeparator : fname

pathSeparator :: Char
pathSeparator = '/'

isPathSeparator :: Char -> Bool
isPathSeparator c = c == '/'
