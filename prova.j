.class public prova
.super java/lang/Object

.field static scanner Ljava/util/Scanner;

;
; standard initializer
.method public <init>()V
   aload_0
   invokenonvirtual java/lang/Object/<init>()V
   return
.end method

; Funzione che legge un intero da standard input
.method public static readInt()I
    .limit locals 0
    .limit stack 2
    
    getstatic prova/scanner Ljava/util/Scanner;
    invokevirtual java/util/Scanner/nextInt()I
    ireturn 

.end method

; Funzione che scrive un intero da standard input
.method public static writeInt(I)V
    .limit locals 1
    .limit stack 3
    
    getstatic java/lang/System/out Ljava/io/PrintStream;
    iload 0
    invokevirtual java/io/PrintStream/println(I)V
    return
    
.end method

.method public static main([Ljava/lang/String;)V
    
    .limit stack 3
    
    ; Inizializzo lo scanner ( serve alla funzione readInt)
    new java/util/Scanner
    astore 1
    aload 1
    getstatic java/lang/System/in Ljava/io/InputStream;
    invokespecial java/util/Scanner/<init>(Ljava/io/InputStream;)V
    aload 1
    putstatic prova/scanner Ljava/util/Scanner;
    ; Fine inizializzazione scanner
    ldc 0
istore 1
invokestatic prova/readInt()I
istore 1
ldc 0
istore 2
invokestatic prova/readInt()I
istore 2
iload 1
iload 2
imul
invokestatic prova/writeInt(I)V
iload 1
ldc 5
iadd
istore 1
iload 1
istore 2
iload 2
ldc 2
iadd
iload 1
imul
ldc 6
imul
iload 1
idiv
istore 2
iload 1
istore 3
iload 3
iload 2
iadd
ldc 2
isub
istore 3
iload 1
invokestatic prova/writeInt(I)V
iload 2
invokestatic prova/writeInt(I)V
.limit locals 5
    return
    
.end method