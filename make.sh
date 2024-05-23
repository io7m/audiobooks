#!/bin/sh

rm -rfv out
mkdir -p out

cp -v src/*.png out/
cp -v src/feed.xml out/
rsync -av src/corrupt out/
rsync -av src/rc1N out/
rsync -av src/rc11 out/
rsync -av src/rcN1 out/
rsync -av src/rcNM out/

