package Controller;

import model.Borrower;
import Service.BorrowerDao;

import java.util.List;
import java.util.UUID;

public class BorrowerController {

	private final BorrowerDao borrowerDao = new BorrowerDao();

	public String saveBorrower(Borrower borrower) {
		return borrowerDao.saveBorrower(borrower);
	}

	public String saveBorrowers(List<Borrower> borrowers) {
		StringBuilder result = new StringBuilder();
		for (Borrower borrower : borrowers) {
			result.append(saveBorrower(borrower)).append("\n");
		}
		return result.toString();
	}

	public List<Borrower> getAllBorrowers() {
		return borrowerDao.getAllBorrowers();
	}

	public Borrower getBorrowerById(UUID id) {
		return borrowerDao.getBorrowerById(id);
	}

	public String deleteBorrower(UUID id) {
		return borrowerDao.deleteBorrower(id);
	}
}
