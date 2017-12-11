
package br.com.handson.apprest.persistence;

import java.util.List;
import javax.persistence.TypedQuery;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;
import br.com.handson.apprest.entity.Competicao;

@PersistenceController
public class CompeticaoDAO extends JPACrud<Competicao, Long> {

	private static final long serialVersionUID = 1L;
	
	
	public List<Competicao> find(String filter) {
		StringBuffer ql = new StringBuffer();
		ql.append("  from Competicao p ");
// use where and or operator as filter		
//		ql.append(" where lower(p.attribute) like :attribute ");
//		ql.append("    or lower(p.anotherAttribute) like :anotherAttribute ");
// list of attributes

	//		modalidade 

	//		local 

	//		dataInicio 

	//		dataFim 

	//		horaInicio 

	//		horaFim 

	//		pais1 

	//		pais2 

	//		etapa 

		TypedQuery<Competicao> query = getEntityManager().createQuery(ql.toString(), Competicao.class);
// use setParameter to fill attributes
//		query.setParameter("attribute", "%" + filter.toLowerCase() + "%");

		return query.getResultList();
	}
}
