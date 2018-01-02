package StandAloneTree;

public interface IModelable {
	public void subscribe(IViewListener view);
	public void unSubscribe(IViewListener view);
}
