<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	import="javax.sql.DataSource, it.unisa.gp.model.DAO.FatturaDS, it.unisa.gp.model.interfaceDS.Fattura, it.unisa.gp.model.bean.FatturaBean,
	it.unisa.gp.model.bean.ClientiBean, it.unisa.gp.model.DAO.TelefonoDS, it.unisa.gp.model.interfaceDS.Telefono, it.unisa.gp.model.bean.TelefonoBean,
	it.unisa.gp.model.DAO.AziendaDS, it.unisa.gp.model.interfaceDS.Azienda, it.unisa.gp.model.bean.AziendaBean,
	it.unisa.gp.model.DAO.AcqContieneAbbDS, it.unisa.gp.model.interfaceDS.AcqContieneAbb, it.unisa.gp.model.bean.AcqContieneAbbBean,
	it.unisa.gp.model.DAO.AcqContieneVidDS, it.unisa.gp.model.interfaceDS.AcqContieneVid, it.unisa.gp.model.bean.AcqContieneVidBean,
	it.unisa.gp.model.DAO.AbbonamentoDS, it.unisa.gp.model.interfaceDS.Abbonamento, it.unisa.gp.model.bean.AbbonamentoBean,
	it.unisa.gp.model.DAO.VideogiocoDS, it.unisa.gp.model.interfaceDS.Videogioco, it.unisa.gp.model.bean.VideogiocoBean,
	
	java.time.format.DateTimeFormatter,	java.time.LocalDateTime, java.util.*"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%
	int idAcq = Integer.parseInt(request.getParameter("idAcq"));
	DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
	Fattura fatturaDS = new FatturaDS(ds);
	FatturaBean fatBean = fatturaDS.doRetrieveByKeyAcquisti(idAcq);
	
	ClientiBean cliente = (ClientiBean) session.getAttribute("utente");
	
	Telefono telefonoDS = new TelefonoDS(ds);
	TelefonoBean telBean = telefonoDS.doRetrieveByKeyCliente(cliente.getCodiceFiscale());
	
	Azienda aziendaDS = new AziendaDS(ds);
	AziendaBean azBean = aziendaDS.doRetrieveByKeyCodFis(cliente.getCodiceFiscale());
	
	AcqContieneAbb acqContieneAbbDS = new AcqContieneAbbDS(ds);
	AcqContieneVid acqContieneVidDS = new AcqContieneVidDS(ds);
	
	Abbonamento abbDS = new AbbonamentoDS(ds);
	Videogioco vidDS = new VideogiocoDS(ds);
	
	Collection<AcqContieneAbbBean> acqContAbb = acqContieneAbbDS.doRetrieveAllAbb(idAcq, null);
	Collection<AcqContieneVidBean> acqContVid = acqContieneVidDS.doRetrieveAllVid(idAcq, null);
		
%>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Fattura</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="icon" type="image/png" sizes="32x32"
	href="img/icon/favicon.png">
<link href="style/style.css" rel="stylesheet">
</head>
<body>
	<script src="script/jquery-3.6.0.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
	<%@ include file="../fragments/header.jsp"%>

	<div class="container">
		<div class="card-body">
			<div class="container mb-5 mt-3">
				<div class="row d-flex align-items-baseline">
					<div class="col-xl-9">
						<p style="color: #7e8d9f; font-size: 20px;">
							Fattura >> <strong>ID: <%= fatBean.getNumero()  %>
							</strong>
						</p>
					</div>
					<div class="col-xl-3 float-end">
						<a class="btn btn-light text-capitalize border-0"
							data-mdb-ripple-color="dark"><i
							class="fas fa-print text-primary"></i> Print</a> <a
							class="btn btn-light text-capitalize"
							data-mdb-ripple-color="dark"><i
							class="far fa-file-pdf text-danger"></i> Export</a>
					</div>
					<hr>
				</div>

				<div class="container">

					<div class="row">
						<div class="col-xl-8">
							<ul class="list-unstyled">
								<li class="text-muted "><b>Intestata a:</b> <span
									style="color: #5d9fc5;"> <%=cliente.getNome() + " " + cliente.getCognome() %>
								</span></li>
								<li class="text-muted"><b>Indirizzo di fatturazione:</b> <%=cliente.getIndFatt() %>
								</li>
								<li class="text-muted"></li>
								<li class="text-muted"><i class="fas fa-phone"></i> <b>Numero
										di telefono:</b> <%=telBean.getNumero() %></li>
								<li class="text-muted"><i class="fas fa-phone"></i> <b>Partita
										Iva:</b> <%=azBean.getpIva() %></li>
								<li class="text-muted"><i class="fas fa-phone"></i> <b>Pec:</b>
									<%=azBean.getPec() %></li>
								<li class="text-muted"><i class="fas fa-phone"></i> <b>Sdi:</b>
									<%=azBean.getSdi() %></li>
							</ul>
						</div>
						<div class="col-xl-4">
							<ul class="list-unstyled">
								<li class="text-muted"><i class="fas fa-circle"
									style="color: #84B0CA;"></i> <span class="fw-bold">ID: </span><%= fatBean.getNumero()%>
								</li>
								<li class="text-muted"><i class="fas fa-circle"
									style="color: #84B0CA;"></i> <span class="fw-bold">Data
										e ora: </span> <%= DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm").format(fatBean.getDataOra())%>
								</li>

							</ul>
						</div>
					</div>

					<div class="row my-2 mx-1 justify-content-center">
						<table class="table table-striped table-borderless">
							<thead style="background-color: #84B0CA;" class="text-white">
								<tr>
									<th scope="col">-</th>
									<th scope="col">Nome</th>
									<th scope="col">Tipo</th>
									<th scope="col">Costo</th>
								</tr>
							</thead>
							<tbody>
								<% for(AcqContieneAbbBean acqAbbBean: acqContAbb){
		    							AbbonamentoBean abbBean = abbDS.doRetrieveByKey(acqAbbBean.getNomeUnivocoAbb());
		    					%>
								<tr>
									<th scope="row"> </th>
									<td> <%= abbBean.getNomeUnivoco() %></td>
									<td> Abbonamento</td>
									<td>&euro; <%= abbBean.getCosto() %></td>
								</tr>
								<%
	            					}
	            				%>
	            				
	            				<% for(AcqContieneVidBean acqVidBean: acqContVid){
		    							VideogiocoBean vidBean = vidDS.doRetrieveByKey(acqVidBean.getCodiceVideogioco());
		    					%>
								<tr>
									<th scope="row"> </th>
									<td> <%= vidBean.getNomeVideogioco() %></td>
									<td> Videogioco</td>
									<td>&euro; <%= vidBean.getCosto() %></td>
								</tr>
								<%
	            					}
	            				%>
	            				
							</tbody>
						</table>
					</div>

					<div class="row">
						<div class="col-xl-3">

							<ul class="list-unstyled">
								<li class="text-muted ms-3"><span class="text-black me-4">Costo	netto</span> &euro; <%=fatBean.getCostoNetto() %>  </li>
								<li class="text-muted ms-3 mt-2"><span
									class="text-black me-4">Iva(22%)</span>&euro; <%=fatBean.getCostoIva() %></li>
							</ul>
							<p class="text-black float-start">
								<span class="text-black me-3"> Totale</span><span
									style="font-size: 25px;">&euro; <%=fatBean.getCostoNetto() + fatBean.getCostoIva()%></span>
							</p>
						</div>
					</div>
					<hr>
				</div>
			</div>
		</div>
	</div>




	<%@ include file="../fragments/footerReg.jsp"%>
</body>
</html>