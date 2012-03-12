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
.method public static sommaAlVettore([III)V
.limit stack 9
.limit locals 4
ldc 0
istore 3
L0: 
iload 1
iload 3
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
aload 0
iload 3
aload 0
iload 3
iaload
iload 2
iadd
iastore
iload 3
ldc 1
iadd
istore 3
goto L0
L2: 
return
.end method
.method public static main([Ljava/lang/String;)V
.limit stack 30
.limit locals 4
    ; Inizializzo lo scanner ( serve alla funzione readInt)
    new java/util/Scanner
    astore 1
    aload 1
    getstatic java/lang/System/in Ljava/io/InputStream;
    invokespecial java/util/Scanner/<init>(Ljava/io/InputStream;)V
    aload 1
    putstatic prova/scanner Ljava/util/Scanner;
    ; Fine inizializzazione scanner
    ldc 4
newarray int
astore 1
ldc 0
istore 2
L5: 
ldc 4
iload 2
isub
ifgt L8
ldc -1
goto L9
L8:
ldc 1
L9: 
ifgt L6
goto L7
L6: 
aload 1
iload 2
invokestatic prova/readInt()I
iastore
iload 2
ldc 1
iadd
istore 2
goto L5
L7: 
aload 1
ldc 4
ldc 5
invokestatic prova/sommaAlVettore([III)V
ldc 0
istore 2
L10: 
ldc 4
iload 2
isub
ifgt L13
ldc -1
goto L14
L13:
ldc 1
L14: 
ifgt L11
goto L12
L11: 
aload 1
iload 2
iaload
invokestatic prova/writeInt(I)V
iload 2
ldc 1
iadd
istore 2
goto L10
L12: 
ldc "ciao"
invokestatic prova/writeString(Ljava/lang/String;)V
return
.end method
