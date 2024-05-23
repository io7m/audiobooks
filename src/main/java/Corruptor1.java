/*
 * Copyright © 2024 Mark Raynsford <code@io7m.com> https://www.io7m.com
 *
 * Permission to use, copy, modify, and/or distribute this software for any
 * purpose with or without fee is hereby granted, provided that the above
 * copyright notice and this permission notice appear in all copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY
 * SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR
 * IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class Corruptor1
{
  private Corruptor1()
  {

  }

  public static void main(
    final String[] args)
    throws IOException
  {
    final var inputFile =
      Paths.get(args[0]);
    final var outputDirectory =
      Paths.get(args[1]);

    for (int fileIndex = 0; fileIndex < 10; ++fileIndex) {
      final int bytes = corrupt(inputFile, fileIndex, outputDirectory);
      System.out.printf("""
{
  "title": "Corrupted %s",
  "href": "https://io7m.github.io/audiobooks/corrupt/%s",
  "duration": 35.0,
  "type": "audio/ogg"
},
                          """.stripIndent().formatted(
        String.format("%d Bytes", Integer.valueOf(bytes)),
        fileNameOf(fileIndex))
      );
    }
  }

  private static int corrupt(
    final Path inputFile,
    final int fileIndex,
    final Path outputDirectory)
    throws IOException
  {
    final var data =
      Files.readAllBytes(inputFile);
    final int count =
      (int) ((data.length * 0.0001) * Math.max(1, fileIndex));

    for (int index = 0; index <= count; ++index) {
      final var offset = (int) (Math.random() * data.length);
      data[offset] = (byte) ((int) (Math.random() * 255.0) & 0xff);
    }

    final var outputFile =
      outputDirectory.resolve(fileNameOf(fileIndex));

    Files.write(outputFile, data);
    return count;
  }

  private static String fileNameOf(final int fileIndex)
  {
    return "item%02d.ogg".formatted(Integer.valueOf(fileIndex));
  }
}
