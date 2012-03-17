    ; Inizializzo lo scanner ( serve alla funzione readInt)
    new java/util/Scanner
    astore 1
    aload 1
    getstatic java/lang/System/in Ljava/io/InputStream;
    invokespecial java/util/Scanner/<init>(Ljava/io/InputStream;)V
    aload 1
    putstatic %className/scanner Ljava/util/Scanner;
    ; Fine inizializzazione scanner
    