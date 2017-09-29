package ruixue.mydictionary.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public interface EntityToDTO<E,D> extends Function<E,D> {
	public default List<D> toDTOs(List<E> entities) {
		List<D> dtos = new ArrayList<>();
		for (E e : entities)
			dtos.add(apply(e));
		return dtos;
	}

}
