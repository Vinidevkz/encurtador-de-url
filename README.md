
# Encurtador de URL

Feito com Spring Framework.


## Screenshots

![App Screenshot](https://dummyimage.com/468x300?text=App+Screenshot+Here)


## API Reference

#### Criar uma nova URL:

```http
  POST /shorter/create-short-url
```

| Parameter | Type     | Example                |
| :-------- | :------- | :------------------------- |
| `originalUrl` | `string` | "www.google.com" |

| Retorno |  
| :-------- | 
| `4sEusbt` |


#### Redirecionamento para a URL original:

```http
  GET /shorter/{hash}
```

| Parameter | Type     | Example                       |
| :-------- | :------- | :-------------------------------- |
| `hash`      | `string` | shorter/4sEusbt |

| Retorno |  
| :-------- | 
| `Header: Location -> www.google.com` |



