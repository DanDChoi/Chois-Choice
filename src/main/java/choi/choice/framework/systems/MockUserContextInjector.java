package choi.choice.framework.systems;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpServletRequest;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MockUserContextInjector implements UserContextInjector {
	String id;
	String userType;
	UserContextInfo info;

	@Override
	public String getCurrentUserId(HttpServletRequest request, Object principal) {
		return id;
	}

	@Override
	public String getCurrentUserId() {
		return id;
	}

	@Override
	public String getCurrentUserType(HttpServletRequest request, Object principal) {
		return userType;
	}

	@Override
	public UserContextInfo getCurrentUser() {
		return info;
	}

	@Override
	public Integer getCurrentUserPurchaseAmountHistory(String queryFrom, String queryTo) {
		return null;
	}

	@Override
	public Integer getCurrentUserPurchaseCountHistory(String queryFrom, String queryTo) {
		return null;
	}
}
