import pandas as pd
import numpy as np

columns = [
    'id',
    'gender',
    'sector',
    'age'
]

raiting_str = "raiting"

seller_data = [['2', 'Masculino', 'Calca',  'Adulto'], ['3', 'Feminino', 'Camiseta',  'Criança'], ['4', 'Feminino', 'Vestido',  'Idoso'], ['5', 'Masculino', 'Short',  'Jovem']]
client_data = [['1', 'Feminino', 'Short', 'Adulto']]

def convert_gender_to_value(gender):
    if gender == 'feminino':
        return 1.5
    elif gender == 'masculino':
        return 0.5
    else:
        return 1

def convert_category_to_value(category):
    if category == 'camiseta':
        return -150
    elif category == 'calca':
        return 200
    elif category == 'short':
        return 350
    elif category == 'vestido':
        return -250
    else:
        return 0

def convert_age_to_value(age):
    if age == 'crianca':
        return 1.05
    elif age == 'jovem':
        return 1.1
    elif age == 'adulto':
        return 0.95
    elif age == 'idoso':
        return 0.9
    else: 
        return 1
    
def get_raiting_match_making(gender, sector, age):
    return ((1000 * gender + sector) / age)

def get_one_raiting(df, index):
    gender = convert_gender_to_value(df["gender"].loc[index].lower())
    sector = convert_category_to_value(df["sector"].loc[index].lower())
    age = convert_age_to_value(df["age"].loc[index].lower())
    return get_raiting_match_making(gender, sector, age)

def get_sellers_raiting_column(seller_df):
    raitingColumn = []
    last_position = len(seller_df.index)
    for i in range(last_position):
        raitingColumn.append(get_one_raiting(seller_df, i))
    return raitingColumn

def get_client_raiting_column(client_df):
    return get_one_raiting(client_df, 0)

def get_index_find_nearest(df, value):
    df = np.asarray(df)
    index = (np.abs(df - value)).argmin()
    return index

def get_seller_to_attendance(seller_df, client_df):
    
    print("\n----------- Sellers ----------")
    print(seller_df, "\n\n")
    print("----------- Client ----------")
    print(client_df, '\n\n')

    seller_df[raiting_str] = get_sellers_raiting_column(seller_df)
    seller_df_sort = seller_df.sort_values(by=[raiting_str], ascending=False)
    seller_df_sort = seller_df_sort.reset_index(drop=True)
    client_df[raiting_str] = get_client_raiting_column(client_df)
    
    print("\n----------- Sellers ----------")
    print(seller_df_sort, "\n\n")
    print("----------- Client ----------")
    print(client_df, '\n\n')

    print("client_df.raiting[0]", client_df.raiting[0])
    raiting_find_nearest = get_index_find_nearest(seller_df_sort.raiting, value=client_df.raiting[0])
    print("raiting_find_nearest", raiting_find_nearest)

    print("Raiting: ", seller_df_sort[raiting_str].loc[raiting_find_nearest], "- Position: ", raiting_find_nearest)
    print("----------- ----------")
    id = seller_df_sort['id'].loc[raiting_find_nearest]
    print("Seller's id: ", id) 
    print("----------- ----------")
    return id

primary_df = pd.DataFrame(seller_data, columns=columns)
secundary_df = pd.DataFrame(client_data, columns=columns)
id = get_seller_to_attendance(primary_df, secundary_df)
print(id)