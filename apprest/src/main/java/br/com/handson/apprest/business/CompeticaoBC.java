package br.com.handson.apprest.business;


import java.util.ArrayList;
import java.util.List;

import br.com.handson.apprest.entity.Competicao;
import br.com.handson.apprest.persistence.CompeticaoDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;


@BusinessController
public class CompeticaoBC extends DelegateCrud<Competicao, Long, CompeticaoDAO> {
	private static final long serialVersionUID = 1L;
	private static int valorInicial = 0;

	public List<Competicao> find(String filter) {
		return getDelegate().find(filter);
	}

	@Override
	public Competicao insert(final Competicao bean) {

		if(valorInicial > 0) {
			String flagCompeticaoMesmoLocal = "N";
			List<Competicao> validaCompeticoes = new ArrayList<>();
			validaCompeticoes = find("");
			for (Competicao competicao : validaCompeticoes) {
				int i = 0;
				if (competicao.getModalidade().equals(validaCompeticoes.get(i).getModalidade())
						&& competicao.getHoraFim().equals(validaCompeticoes.get(i).getHoraInicio())) {
					flagCompeticaoMesmoLocal = "S";
					try {
						throw new Exception();
					} catch (Exception e) {
						throw new RuntimeException("Erro, existem competições cadastradas para este Local e horario",
								e);
					}
				}
				i++;
			}

			if ("N".equals(flagCompeticaoMesmoLocal)) {
				
				for (Competicao competicao : validaCompeticoes) {
					if (competicao.getEtapa() == "Eliminatorias" 
							|| competicao.getEtapa() == "Oitavas de Final"
							|| competicao.getEtapa() == "Quartas de Final"
							&& competicao.getHoraInicio().equals(competicao.getHoraFim())) {
						try {
							throw new Exception();
						} catch (Exception e) {
							throw new RuntimeException("Erro, existem competições cadastradas para este local", e);
						}

					}

				}
			}
		}
		valorInicial++;
		return getDelegate().insert(bean);

	}

}