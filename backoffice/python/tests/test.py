import csv
if __name__ == "__main__":
    with open('explanations.csv', mode='r', encoding='utf-8') as file:
        reader = csv.DictReader(file)
        for row in reader:
            print(row['*explanation.lexical_item_id (lexical_item.id)'])