import java.util.*;
import java.io.*;


class Solution {
  static void main(InputReader in, OutputWriter out) {
    int tc = in.nextInt();
    for (int t = 1; t <= tc; t++) {
      out.println(solution());
    }
  }

  static String solution() {
    return "OK";
  }
}


// Driver program to read from file/System.in and increase stack size
public class Main implements Runnable {
  private final InputReader in;
  private final OutputWriter out;

  private Main(InputReader ir, OutputWriter or) {
    in = ir;
    out = or;
  }

  public static void main(String[] args) throws Exception {
    InputStream is = args.length > 0 ? new FileInputStream(args[0]) : System.in;
    InputReader in = new InputReader(is);
    OutputWriter out = new OutputWriter(System.out);
    Main r = new Main(in, out);
    new Thread(null, r, "Main", 1 << 26).start(); // 64 mb stack size
  }

  @Override
  public void run() {
    try {
      Solution.main(in, out);
    } finally {
      out.close();
    }
  }
}

// Fast I/O
// Taken and modified from here:
// https://www.quora.com/What-is-the-best-way-in-Java-to-take-input-and-write-output-for-an-Online-Judge
class InputReader {
  private InputStream is;
  private byte[] buff = new byte[8 * 1024];
  private int curChar;
  private int numChars;

  InputReader(InputStream is) {
    this.is = is;
  }

  int read() {
    if (numChars == -1) throw new InputMismatchException();
    if (curChar >= numChars) {
      curChar = 0;
      try {
        numChars = is.read(buff);
      } catch (IOException e) {
        throw new InputMismatchException();
      }
      if (numChars <= 0) return -1;
    }
    return buff[curChar++];
  }

  int nextInt() {
    int c = read();
    while (isSpaceChar(c)) c = read();
    int sgn = 1;
    if (c == '-') {
      sgn = -1;
      c = read();
    }
    int res = 0;
    do {
      if (c < '0' || c > '9')
        throw new InputMismatchException();
      res *= 10;
      res += c - '0';
      c = read();
    } while (!isSpaceChar(c));
    return res * sgn;
  }

  String nextString() {
    int c = read();
    while (isSpaceChar(c)) c = read();
    StringBuilder res = new StringBuilder();
    do {
      res.appendCodePoint(c);
      c = read();
    } while (!isSpaceChar(c));
    return res.toString();
  }

  double nextDouble() {
    int c = read();
    while (isSpaceChar(c)) c = read();
    int sgn = 1;
    if (c == '-') {
      sgn = -1;
      c = read();
    }
    double res = 0;
    while (!isSpaceChar(c) && c != '.') {
      if (c == 'e' || c == 'E')
        return res * Math.pow(10, nextInt());
      if (c < '0' || c > '9')
        throw new InputMismatchException();
      res *= 10;
      res += c - '0';
      c = read();
    }
    if (c == '.') {
      c = read();
      double m = 1;
      while (!isSpaceChar(c)) {
        if (c == 'e' || c == 'E') return res * Math.pow(10, nextInt());
        if (c < '0' || c > '9') throw new InputMismatchException();
        m /= 10;
        res += (c - '0') * m;
        c = read();
      }
    }
    return res * sgn;
  }

  long nextLong() {
    int c = read();
    while (isSpaceChar(c)) c = read();
    int sgn = 1;
    if (c == '-') {
      sgn = -1;
      c = read();
    }
    long res = 0;
    do {
      if (c < '0' || c > '9') throw new InputMismatchException();
      res *= 10;
      res += c - '0';
      c = read();
    } while (!isSpaceChar(c));
    return res * sgn;
  }

  boolean isSpaceChar(int c) {
    return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
  }

  String next() {
    return nextString();
  }

  int[] nextIntArray(int size) {
    int[] arr = new int[size];
    for (int i = 0; i < size; i++) arr[i] = nextInt();
    return arr;
  }
}
class OutputWriter {
  private final PrintWriter writer;

  OutputWriter(OutputStream outputStream) {
    writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
  }

  OutputWriter(Writer writer) {
    this.writer = new PrintWriter(writer);
  }

  void print(Object... objects) {
    for (int i = 0; i < objects.length; i++) {
      if (i != 0) writer.print(' ');
      writer.print(objects[i]);
    }
  }

  void println(Object... objects) {
    print(objects);
    writer.println();
  }

  void printf(String format, Object... objects) {
    writer.printf(format, objects);
  }

  void printfln(String format, Object... objects) {
    writer.printf(format + "\n", objects);
  }

  void close() {
    writer.close();
  }

  void flush() {
    writer.flush();
  }
}