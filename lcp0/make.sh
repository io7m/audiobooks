#!/bin/sh

faketime '2024-01-01T00:00:00' \
zip -0 \
book.zip \
manifest.json \
track1.ogg \
track2.ogg \
track3.ogg
