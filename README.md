# Search service

For this exercise, I implemented these endpoints  

**URL** : `/weather/detail?id={id}&country={country}`
 `/weather/detail?id={id}&name={name}`

**Method** : `Get`

## Success Response

**Code** : `200 OK`

**Content example**

```json
{
    "code": 0,
    "timestamp": 1687703027128,
    "data": {
        "student": {
            "id": 1,
            "name": "Kim"
        },
        "universities": [
            {
                "country": "China",
                "name": "Sun Yat-Sen University",
                "domains": [
                    "mail2.sysu.edu.cn",
                    "mail.sysu.edu.cn"
                ],
                "alpha_two_code": "CN",
                "state-province": null,
                "web_pages": [
                    "https://sysu.edu.cn"
                ]
            },
            {
                "country": "China",
                "name": "Hunan University",
                "domains": [
                    "hnu.edu.cn",
                    "www-en.hnu.edu.cn"
                ],
                "alpha_two_code": "CN",
                "state-province": null,
                "web_pages": [
                    "https://www.hnu.edu.cn",
                    "http://www-en.hnu.edu.cn"
                ]
            },
            ...
        ]
    }
}
```

## Error Response

**Condition** : If cannot find anything.

**Code** : `404 NOT FOUND`

**Content** :

```json
{
    "code": 0,
    "timestamp": 1687703103671,
    "data": {
        "student": null,
        "universities": []
    }
}
```


# End point for testing Hystrix

**URL** : `/weather/fallback`

**Method** : `Get`

## Success Response

**Code** : `500 INTERNAL SERVER ERROR`

**Content example**

```json
{
    "code": 0,
    "timestamp": 1687703292430,
    "data": "There is a fallback!"
}
```
