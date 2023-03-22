# Inventory App

2 adet profil ekledim.

ilk profil canli , 2. test icin.

Controller'da dönen metodun pek amacına uymasada bir unit testini yazdim. Integration test yok. Mock kullanmadım. Onun yerinde docker compose olarak ayri bir test db ortamı kullandım.

canli mysql : docker-compose -f mysql-compose.yml up
test mysql : docker-compose -f mysql-compose-test.yml up


Katman olarak;

Entity -> Repository -> Service kullandım

Demo amaclı oldugundan Service - ServiceImpl ve DTO şablonuna girmedim.

Controller'da dönen veri için normal stream.map kullandım.



Sorunuz olursa umitkirtil@gmail.com üzerinden iletişime geçebilirsiniz.
