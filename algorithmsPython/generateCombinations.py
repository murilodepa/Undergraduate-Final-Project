# Um programa Python para imprimir todas as
# combinações usando a função da biblioteca itertools
from itertools import product
from ntpath import join

products = ["Camiseta", "Calça", "Short", "Vestido", "Empate"]

combinations_list = []
combinations_str = ""
cont = 0

get_combinations = product(products, repeat=5) #Especificar a quantidade de produtos que cada combinação terá

for combination in get_combinations:
    cont = cont + 1
    for i in range(5):
        if i == 4:
            combinations_list.append(combination[i])
        else:
            combinations_list.append(combination[i] + ",")
    combinations_str = ''.join(combinations_list)
    print(combinations_str)
    combinations_list = []
print("Quantidade de combinações: ", cont)


