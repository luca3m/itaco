def BubbleSort(a, dim)
scambio=0
scambio = 1
while scambio > 0 do 
scambio = 0
i=0
i = 0
while i < (dim - 1) do 
if a[i] > a[i + 1]
tmp=0
tmp = a[i]
tmp2=0
tmp2 = a[i + 1]
a[i + 1] = tmp
a[i] = tmp2
scambio = 1

end
i = i + 1

end

end

end
n=0
n = 0
vet= [3]
while n < 3 do 
vet[n] = gets
n = n + 1

end
BubbleSort(vet,3)
n2=0
n2 = 0
while n2 < 3 do 
n2 = n2 + 1

end
