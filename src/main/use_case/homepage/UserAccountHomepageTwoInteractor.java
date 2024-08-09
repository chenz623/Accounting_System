package use_case.homepage;

import com.sun.security.auth.NTSidUserPrincipal;
import data_access.account.UserAccountDataAccessInterface;
import entity.account.UserAccount;

public class UserAccountHomepageTwoInteractor extends HomepageTwoInteractor<
        UserAccountDataAccessInterface,
        UserAccountHomepageTwoOutputBoundary,
        UserAccountHomepageTwoInputData,
        UserAccountHomepageTwoOutputData> implements UserAccountHomepageTwoInputBoundary {

    public UserAccountHomepageTwoInteractor(UserAccountDataAccessInterface userDataAccessObject,
                                            UserAccountHomepageTwoOutputBoundary presenter) {
        super(userDataAccessObject, presenter);
    }

    @Override
    public void execute(UserAccountHomepageTwoInputData inputData) {
        String id = inputData.getId();
        UserAccount user = this.userDataAccessObject.getById(id);
        String username = user.getUsername();
        String totalIncome = String.valueOf(user.getTotalIncome());
        String totalOutflow = String.valueOf(user.getTotalOutflow());
        String totalBalance = String.valueOf(user.getTotalBalance());

        String[] basicUserInfo = {username, totalIncome, totalOutflow, totalBalance};
        UserAccountHomepageTwoOutputData outputData = new UserAccountHomepageTwoOutputData(basicUserInfo);
        this.presenter.prepareSuccessView(outputData);
    }
}