package service;

import entity.Branch;
import exception.NotFoundClassException;
import repository.BranchRepository;

import java.util.List;

public class BranchService {

    BranchRepository branchRepository = new BranchRepository();

    public void save(String name) {
        Branch branch = new Branch(name);
        branchRepository.saveOrUpdate(branch);
    }

    public void saveOrUpdate(Branch branch) {
        branchRepository.saveOrUpdate(branch);
    }

    public void delete (Branch branch) {
        branchRepository.delete(branch);
    }

    public Branch loadById(Long id) {
        Branch loadedBranches = branchRepository.loadById(id);
        if (loadedBranches == null) {
            throw new NotFoundClassException("there is no branch with this id...!!");
        }
        return loadedBranches;
    }

    public List<Branch> loadByName(String name) {
        List<Branch> branches = branchRepository.loadByName(name);
        if (branches == null) {
            throw new NotFoundClassException("there is no branch with this customer name...!!");
        }
        return branches;
    }

}
