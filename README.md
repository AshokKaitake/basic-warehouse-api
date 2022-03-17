# Basic Warehouse API
Basic Warehouse REST API's to manipulate inventory of the warehouse

### Assignment details
The assignment is to implement a warehouse software. This software should hold articles, and the articles should contain an identification number, a name and available stock.
It should be possible to load articles into the software from a file, see the attached inventory.json.
The warehouse software should also have products, products are made of different articles. Products should have a name, price and a list of articles of which they are made from with a quantity.
The products should also be loaded from a file, see the attached products.json.

The warehouse should have at least the following functionality;
* Get all products and quantity of each that is an available with the current inventory
* Remove(Sell) a product and update the inventory accordingly

### Approach
Expose an interactive REST API to manipulate inventory of the warehouse

### Usage

1. Get this repository in local
   ```shell
   git clone git@github.com:AshokKaitake/basic-warehouse-api.git
   ```
2. Enter into folder created from git clone
   ```shell
   cd basic-warehouse-api.git
   ```
3. install `maven` dependencies
   ```shell
   mvn install
   ``` 
3. run application
   ```shell
   mvn spring-boot:run
   ```
### Prerequisite
1. Java 8 or higher version
2. PostgresSQL 13.6 or higher version
3. Maven 3.6.3 or higher
> Update Database connection information in application.properties
### Database migration
> For database migration we are using `Flyway` an open-source database migration tool

Things to follow for migration scripts:
- All migration scripts (`.sql`) should go in `resources/db/migration` location
- Script should follow `V<version-number>__<description>.sql` this format.
    - Example, `V1.0.0__initial_script.sql`
- Once migration script is applied you should not change anything in existing script. _If you do so, it will throw an error_.
- Create new migration file with different version number for any further DB operations.

### API Details


<details>
<summary>

#### 1. `GET /api/v1/articles` _Get all articles from inventory_
</summary>
<p>

###### Request
```curl
curl http://localhost:8080/api/v1/articles
```
</p>
<p>

###### Response
```
{
    "responseCode": 200,
    "responseMessage": "Record found successfully",
    "responseData": [
        {
            "name": "leg",
            "stock": 12,
            "art_id": "1"
        },
        {
            "name": "screw",
            "stock": 17,
            "art_id": "2"
        },
        {
            "name": "seat",
            "stock": 2,
            "art_id": "3"
        },
        {
            "name": "table top",
            "stock": 1,
            "art_id": "4"
        }
    ]
}
```
</p>
</details>

<details>
<summary>

#### 2. `GET /api/v1/products` _Get all products_
</summary>
<p>

###### Request
```curl
curl http://localhost:8080/api/v1/products
```
</p>
<p>

###### Response
```
{
    "responseCode": 200,
    "responseMessage": "Record found successfully",
    "responseData": [
        {
            "name": "Dining Chair",
            "contain_articles": [
                {
                    "art_id": "1",
                    "amount_of": 4
                },
                {
                    "art_id": "2",
                    "amount_of": 8
                },
                {
                    "art_id": "3",
                    "amount_of": 1
                }
            ]
        },
        {
            "name": "Dinning Table",
            "contain_articles": [
                {
                    "art_id": "1",
                    "amount_of": 4
                },
                {
                    "art_id": "2",
                    "amount_of": 8
                },
                {
                    "art_id": "4",
                    "amount_of": 1
                }
            ]
        }
    ]
}
```
</p>
</details>

<details>
<summary>

#### 3. `GET /api/v1/available/products` _Get all products and quantity of each that is an available with the current inventory_
</summary>
<p>

###### Request
```curl
curl http://localhost:8080/api/v1/available/products
```
</p>
<p>

###### Response
```
{
    "responseCode": 200,
    "responseMessage": "Record found successfully",
    "responseData": [
        {
            "name": "Dining Chair",
            "isAvailable": true,
            "quantityInStock": 2,
            "contain_articles": [
                {
                    "stock": 12,
                    "art_id": "1",
                    "amount_of": 4
                },
                {
                    "stock": 17,
                    "art_id": "2",
                    "amount_of": 8
                },
                {
                    "stock": 2,
                    "art_id": "3",
                    "amount_of": 1
                }
            ]
        },
        {
            "name": "Dinning Table",
            "isAvailable": true,
            "quantityInStock": 1,
            "contain_articles": [
                {
                    "stock": 12,
                    "art_id": "1",
                    "amount_of": 4
                },
                {
                    "stock": 17,
                    "art_id": "2",
                    "amount_of": 8
                },
                {
                    "stock": 1,
                    "art_id": "4",
                    "amount_of": 1
                }
            ]
        }
    ]
}
```
</p>
</details>

<details>
<summary>

#### 4. `DELETE /api/v1/sell/product` _Remove(Sell) a product and update the inventory accordingly_
</summary>
<p>

###### Request
```curl
curl --location --request DELETE 'http://localhost:8080/api/v1/sell/product?productName=Dinning Table'
```
</p>
<p>

###### Response
```
{
    "responseCode": 200,
    "responseMessage": "Product has been sold",
    "responseData": {
        "name": "Dinning Table",
        "contain_articles": [
            {
                "art_id": "1",
                "amount_of": 4
            },
            {
                "art_id": "2",
                "amount_of": 8
            },
            {
                "art_id": "4",
                "amount_of": 1
            }
        ]
    }
}
```
</p>
</details>

<details>
<summary>

#### 5.`POST /api/v1/import/inventoryfile` _import articles from json file_
</summary>
<p>

###### Request
```curl
curl --location --request POST 'http://localhost:8080/api/v1/import/inventoryfile' --form 'file=@"<filepath>/inventory.json";type=application/json'
```
> Kindly change file path as per file location
</p>

<p>

###### Response
```
{
    "responseCode": 200,
    "responseMessage": "Record successfully imported",
    "responseData": [
        {
            "name": "leg",
            "stock": 12,
            "art_id": "1"
        },
        {
            "name": "screw",
            "stock": 17,
            "art_id": "2"
        },
        {
            "name": "seat",
            "stock": 2,
            "art_id": "3"
        },
        {
            "name": "table top",
            "stock": 1,
            "art_id": "4"
        }
    ]
}
```
</p>
</details>
<details>
<summary>

#### 6. `POST /api/v1/import/productfile` _import product from json file_
</summary>
<p>

##### Request
```curl
curl --location --request POST 'http://localhost:8080/api/v1/import/productfile' --form 'file=@"<filepath>/products.json";type=application/json'
```
> Kindly change file path as per file location
>
</p>
<p>

###### Response
```
{
    "responseCode": 200,
    "responseMessage": "Record successfully imported",
    "responseData": [
        {
            "name": "Dining Chair",
            "contain_articles": [
                {
                    "art_id": "1",
                    "amount_of": 4
                },
                {
                    "art_id": "2",
                    "amount_of": 8
                },
                {
                    "art_id": "3",
                    "amount_of": 1
                }
            ]
        },
        {
            "name": "Dinning Table",
            "contain_articles": [
                {
                    "art_id": "1",
                    "amount_of": 4
                },
                {
                    "art_id": "2",
                    "amount_of": 8
                },
                {
                    "art_id": "4",
                    "amount_of": 1
                }
            ]
        }
    ]
}
```
</p>
</details>
