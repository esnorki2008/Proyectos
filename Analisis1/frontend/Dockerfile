#este archivo se encuentra junto al contenido de nuestro proyecto 

#base image #imagen a utilizar ya que cuenta con nodejs interno
FROM node:12-alpine  

#directorio de trabajo y variables del sistema utilizadas en Nodejs
WORKDIR /usr/src/app 
ARG NODE_ENV
ENV NODE_ENV $NODE_ENV

# copia e intalacion de todos los modulos necesarios para nuestra app
COPY package.json /usr/src/app/
RUN npm install && npm install @angular/cli

#copiamos nuestra App a nuestro directorio de trabajo
COPY . /usr/src/app

#Comando que se ejecutara al momento de Correr nuestra imagen de Docker
CMD ["npm", "start" ]

# Puerto en el cual se expondra nuestra imagen al ser ejecutada
EXPOSE 4200
