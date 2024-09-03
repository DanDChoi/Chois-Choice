package choi.choice.framework.systems;

import javax.servlet.http.HttpServletRequest;

public interface UserContextInjector {
    String getCurrentUserId();

   	String getCurrentUserId(HttpServletRequest request, Object principal);

   	String getCurrentUserType(HttpServletRequest request, Object principal);

   	UserContextInfo getCurrentUser();

   	Integer getCurrentUserPurchaseAmountHistory(String queryFrom, String queryTo);

   	Integer getCurrentUserPurchaseCountHistory(String queryFrom, String queryTo);
}
