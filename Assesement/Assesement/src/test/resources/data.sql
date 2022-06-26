create table NACE (Id bigint not null, Code varchar(255), Description varchar(255), Level integer, OrderId integer, Parent varchar(255), Reference varchar(255), Rulings varchar(255), This_Item_Also_Includes varchar(255), This_Item_Excludes varchar(255), This_Item_Includes varchar2(1000), primary key (id));
create sequence hibernate_sequence start with 1 increment by 1 ;



INSERT INTO NACE (Id ,Code , Description , Level , OrderId , Parent , Reference , Rulings , This_Item_Also_Includes , This_Item_Includes , This_Item_Excludes) VALUES (4000 ,'A' ,'AGRICULTURE, FORESTRY AND FISHING' ,'1' ,'398481' ,'' ,'A' ,'' ,'' ,'This section includes the exploitation of vegetal and animal natural resources, comprising the activities of growing of crops, raising and breeding of animals, harvesting of timber and other plants, animals or animal products from a farm or their natural habitats.' ,'' );
