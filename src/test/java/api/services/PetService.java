package api.services;

import api.endpoints.PetAPIObject;
import api.payload.APIResponse;
import api.payload.Pet;

public class PetService extends BaseService {

    public Pet createPet(Pet payload) {
        response = PetAPIObject.post(payload);
        response.then().log().all();
        return getEntity(Pet.class);
    }

    public <E> E getPetById(String petId, Class<E> entityClass) {
        response = PetAPIObject.get(petId);
        response.then().log().all();
        return getEntity(entityClass);
    }

    public Pet updatePet(Pet payload) {
        response = PetAPIObject.update(payload);
        response.then().log().all();
        return getEntity(Pet.class);
    }

    public APIResponse deletePetByPetId(String petId) {
        response = PetAPIObject.delete(petId);
        response.then().log().all();
        return getEntity(APIResponse.class);
    }
}
