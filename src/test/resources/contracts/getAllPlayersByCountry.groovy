package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {

    description "should return all Player for Specific Country"

    request {
        urlPath( "/api/fetch-all-players-by-country")
        {
            queryParameters {
                parameter("idCountry", "91")
            }
        }
        method GET()
    }

    response {
        status OK()
        headers {
            contentType applicationJson()
        }
        body(
                file("files/playersByCountry.json")
        )
    }
}