# phlogiston
front-end created by https://github.com/Chernikov-Daniil
back-end created by https://github.com/SterkhovAV

### before start
- jdk 1.8+
- PostgreSQL
- change in "application.properties"

       spring.datasource.url=your DB adress
       spring.datasource.username=your login
       spring.datasource.password=your password

- if you don't want to change base-login-password you can create local data base and user
  start SQL Shell (login to database postgres under login postgres)

       CREATE DATABASE phlogiston;
       CREATE USER phlogiston_user WITH PASSWORD 'password';
       GRANT ALL PRIVILEGES ON DATABASE phlogiston to phlogiston_user;
       \c phlogiston;
       CREATE SCHEMA phlogiston;
       GRANT ALL PRIVILEGES ON SCHEMA phlogiston to phlogiston_user;

  now data base and user created

### problems solving
- data base drop instruction
  start SQL Shell (login to database postgres under login postgres)

        DROP DATABASE phlogiston;
        CREATE DATABASE phlogiston;
        GRANT ALL PRIVILEGES ON DATABASE phlogiston to phlogiston_user;
        \c phlogiston;
        CREATE SCHEMA phlogiston;
        GRANT ALL PRIVILEGES ON SCHEMA phlogiston to phlogiston_user;

### API

API
Base address - http://localhost:7080/ (you can change it in "application.properties")


Count one phase params - http://localhost:7080/onePhase/calc (POST)
Request:

{
"pressure": 10.0,
"temperature": 400,
"specificVolumeConfim": true,
"densityConfim": true
}

!! Temperature in K !!
!! Pressure in MPa !!


Can be requested this params: 
- specificVolumeConfim 
- densityConfim 
- specificEntropyConfim 
- specificEnthalpyConfim 
- specificInternalEnergyConfim 
- specificHeatCapacityPConfim 
- specificHeatCapacityVConfim

Answer:

{
"pressure": 10.0,
"temperature": 400.0,
"specificVolume": 0.017540093214404435,
"density": 57.012239774117674,
"specificEntropy": null,
"specificEnthalpy": null,
"specificInternalEnergy": null,
"specificHeatCapacityP": null,
"specificHeatCapacityV": null
}

Count one phase params - http://localhost:7080/onePhase/calc (POST)



