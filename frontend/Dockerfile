# Etapa de build con Node 18.20.5
FROM node:18.20.5 as build

# Crear directorio de trabajo
WORKDIR /app

# Copiar dependencias
COPY package*.json ./
RUN npm install

# Copiar el resto del proyecto
COPY . .

# Build de producción
RUN npm run build --prod

# Etapa de producción usando NGINX
FROM nginx:alpine

# Copiar archivos del build Angular al servidor NGINX
COPY --from=build /app/dist/sakai-ng /usr/share/nginx/html

# Exponer puerto 80
EXPOSE 80

# Iniciar nginx
CMD ["nginx", "-g", "daemon off;"]
