package rx.practice.ejb;

import javax.ejb.Stateless;

@Stateless
public class RemoteEJBImpl implements RemoteEJB {

	@Override
	public void test() {
		System.out.println("remote EJB!!!!!!!!!!!!!!!!!!!!!!!!!!");
	}
}
