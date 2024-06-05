#!/bin/sh

rm -rfv out
mkdir -p out

cp -v src/*.png out/
cp -v src/feed.xml out/
cp -v src/index.html out/
cp -v src/style.css out/

rsync -av src/corrupt out/
rsync -av src/rc1N out/
rsync -av src/rc11 out/
rsync -av src/rcN1 out/
rsync -av src/rcNM out/
rsync -av src/lcp0 out/
rsync -av src/missing out/
