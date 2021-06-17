package contracts
import org.springframework.cloud.contract.spec.Contract
Contract.make {
    request {
        method PUT()
        url '/persons'
        body([
                "id": "20",
                "firstName": "Giel",
                "lastName": "Reynders",
                "age": "99"
        ])
        headers {
            contentType applicationJson()
        }
    }
    response {
        status CREATED()
        body([
                "id": "20",
                "firstName": "Giel",
                "lastName": "Reynders",
                "age": "40"
        ])
    }
}
