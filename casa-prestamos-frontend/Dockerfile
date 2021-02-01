# stage 1
FROM node:latest as node
WORKDIR /app
COPY . .
RUN npm install
RUN npm run build --prod

# stage 2
FROM nginx:alpine
COPY --from=node /app/dist/app-base /usr/share/nginx/html

# FROM node:10-alpine
# WORKDIR /app
# COPY --from=builder /app ./
# CMD ["npm", "run", "start:prod"]
