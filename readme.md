# うさげ

1.  `javac *.java`
2.  `java SessionManager`
3.  open another terminal, and `java ClientTester`
4.  type something
5.  try multiple terminals

## Protocol (tmp)

1.  header

    1.  from
        -   username

    2.  type
        -   `TEXT` (@ editor) or `CHAT` or `CURSOR`

2.  content
    -   ...

e.g.

-   `[CHAT,Alice]Hello World!`
-   `[TEXT,Bob]int main(){\nreturn 0;\n}`
-   `[CURSOR,Chris]6:10`
