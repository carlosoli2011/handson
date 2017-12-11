
$(function() {
	$("#delete").hide();

	if (id = App.getUrlParameterByName('id')) {
		CompeticaoProxy.load(id).done(loadOk).fail(loadFailed);
	}

	MetadataProxy.getDemoiselleVersion().done(function(data) {
		$("#demoiselle-version").html(data);
	});

	$("form").submit(function(event) {
		event.preventDefault();
	});	
	$("#save").click(function() {
	var data = {

			modalidade : $("#modalidade").val(),

			local : $("#local").val(),

			dataInicio : $("#dataInicio").val(),

			dataFim : $("#dataFim").val(),

			horaInicio : $("#horaInicio").val(),

			horaFim : $("#horaFim").val(),

			pais1 : $("#pais1").val(),

			pais2 : $("#pais2").val(),

			etapa : $("#etapa").val(),

		};
		if (id = $("#id").val()) {
			CompeticaoProxy.update(id, data).done(saveOk).fail(saveFailed);
		} else {
			CompeticaoProxy.insert(data).done(saveOk).fail(saveFailed);
		}
	});

	$("#delete").click(function() {
		bootbox.confirm("Tem certeza que deseja apagar?", function(result) {
			if (result) {
				CompeticaoProxy.remove([ $("#id").val() ]).done(removeOk);
			}
		});
	});

	$("#back").click(function() {
		history.back();
	});


	$("#dataInicio").datepicker({
	showButtonPanel : true,
	dateFormat : "yy-dd-mm",
	regional : "pt-BR"
});

	$("#dataFim").datepicker({
	showButtonPanel : true,
	dateFormat : "yy-dd-mm",
	regional : "pt-BR"
});


});

function loadOk(data) {
	$("#id-row").show();
	$("#id-text").html(data.id);
	$("#id").val(data.id);
	
	$("#id").val(data.id);

	$("#modalidade").val(data.modalidade);

	$("#local").val(data.local);

	$("#dataInicio").val(data.dataInicio);

	$("#dataFim").val(data.dataFim);

	$("#horaInicio").val(data.horaInicio);

	$("#horaFim").val(data.horaFim);

	$("#pais1").val(data.pais1);

	$("#pais2").val(data.pais2);

	$("#etapa").val(data.etapa);
	
	$("#delete").show();
}

function loadFailed(request) {
	switch (request.status) {
		case 404:
			bootbox.alert("Você está tentando acessar um registro inexistente!", function() {
				location.href = "competicao-list.html";
			});
			break;

		default:
			break;
	}
}

function saveOk(data) {
	location.href = 'competicao-list.html';
}

function saveFailed(request) {
	switch (request.status) {
		case 422:
			$($("form input").get().reverse()).each(function() {
				var id = $(this).attr('id');
				var message = null;

				$.each(request.responseJSON, function(index, value) {
					if (id == value.property) {
						message = value.message;
						return;
					}
				});

				if (message) {
					$("#" + id + "-message").html(message).show();
					$(this).focus();
				} else {
					$("#" + id + "-message").hide();
				}
			});
			break;
		default:
			break;
	}
}
function removeOk(data) {
	location.href = 'competicao-list.html';
}