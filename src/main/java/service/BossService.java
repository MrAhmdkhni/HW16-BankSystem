package service;

import entity.Boss;
import exception.NotFoundClassException;
import repository.BossRepository;

public class BossService {

    BossRepository bossRepository = new BossRepository();

    public void save(String firstname, String lastname, String nationalCode, String mobileNumber, String username, String password) {
        Boss boss = new Boss(firstname, lastname, nationalCode, mobileNumber, username, password);
        bossRepository.saveOrUpdate(boss);
    }

    public void update(Boss boss) {
        bossRepository.saveOrUpdate(boss);
    }

    public void delete(Boss boss) {
        bossRepository.delete(boss);
    }

    public void deleteByNationalCode(String nationalCode) {
        bossRepository.deleteByNationalCode(nationalCode);
    }

    public  Boss loadById(Long id) {
        Boss bossLoaded = bossRepository.loadById(id);
        if (bossLoaded == null) {
            throw new NotFoundClassException("there is no boss with this id...!!");
        }
        return bossLoaded;
    }

    public Boss loadByNationalCodeAndUser(String nationalCode, String username) {
        Boss bossLoaded = bossRepository.loadByNationalCodeAndUser(nationalCode, username);
        if (bossLoaded == null) {
            throw new NotFoundClassException("there is no boss with this national code and username...!!");
        }
        return bossLoaded;
    }

}
