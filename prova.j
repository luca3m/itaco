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
    invokevirtual java/io/PrintStream/print(I)V
    getstatic java/lang/System/out Ljava/io/PrintStream;
    ldc " "
    invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V
    return
    
.end method

; Funzione che scrive un intero da standard input
.method public static writeString(Ljava/lang/String;)V
    .limit locals 1
    .limit stack 3
    
    getstatic java/lang/System/out Ljava/io/PrintStream;
    aload 0
    invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V
    return
    
.end method

.method public static main([Ljava/lang/String;)V
    .limit locals 100
    .limit stack 30
    
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
ldc "Inserisci un numero: "
invokestatic prova/writeString(Ljava/lang/String;)V
invokestatic prova/readInt()I
istore 1
ldc 1
istore 2
L0: 
iload 1
ldc 0
isub
ifgt L3
ldc -1
goto L4
L3:
ldc 1
L4: 
ifgt L1
goto L2
L1: 
iload 1
iload 2
imul
istore 2
iload 1
ldc 1
isub
istore 1
goto L0
L2: 
ldc "Il fattoriale e:"
invokestatic prova/writeString(Ljava/lang/String;)V
iload 2
invokestatic prova/writeInt(I)V
ldc "
"
invokestatic prova/writeString(Ljava/lang/String;)V
    return
    
.end method