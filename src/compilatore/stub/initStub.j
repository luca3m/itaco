.class public %className
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
    
    getstatic %className/scanner Ljava/util/Scanner;
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
