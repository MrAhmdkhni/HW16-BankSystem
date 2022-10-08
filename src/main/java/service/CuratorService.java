package service;

import entity.Curator;
import exception.NotFoundClassException;
import repository.CuratorRepository;

public class CuratorService {

    CuratorRepository curatorRepository = new CuratorRepository();

    public void save(String firstname, String lastname, String nationalCode, String mobileNumber, String username, String password) {
        Curator curator = new Curator(firstname, lastname, nationalCode, mobileNumber, username, password);
        curatorRepository.saveOrUpdate(curator);
    }

    public void update(Curator curator) {
        curatorRepository.saveOrUpdate(curator);
    }

    public void delete(Curator curator) {
        curatorRepository.delete(curator);
    }

    public void deleteByNationalCode(String nationalCode) {
        curatorRepository.deleteByNationalCode(nationalCode);
    }

    public  Curator loadById(Long id) {
        Curator curatorLoaded = curatorRepository.loadById(id);
        if (curatorLoaded == null) {
            throw new NotFoundClassException("there is no curator with this id...!!");
        }
        return curatorLoaded;
    }

    public Curator loadByNationalCodeAndUser(String nationalCode, String username) {
        Curator curatorLoaded = curatorRepository.loadByNationalCodeAndUser(nationalCode, username);
        if (curatorLoaded == null) {
            throw new NotFoundClassException("there is no curator with this national code and username...!!");
        }
        return curatorLoaded;
    }

}
