# Basic Warehouse API
Basic Warehouse REST API's to manipulate inventory of the warehouse

### Assignment details
The assignment is to implement a warehouse software. This software should hold articles, and the articles should contain an identification number, a name and available stock. It should be possible to load articles into the software from a file, see the attached inventory.json.
The warehouse software should also have products, products are made of different articles. Products should have a name, price and a list of articles of which they are made from with a quantity. The products should also be loaded from a file, see the attached products.json.

The warehouse should have at least the following functionality;
* Get all products and quantity of each that is available with the current inventory
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