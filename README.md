
# Encurtador de URL

Feito com Spring Framework, fiz esse projeto para entender melhor os conceitos de Arquitetura e Escalabilidade.

![Resume IA](https://github.com/Vinidevkz/encurtador-de-url/raw/v1/src/img/shorterSD.png)


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



