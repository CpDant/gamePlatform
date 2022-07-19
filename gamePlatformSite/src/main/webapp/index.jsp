<%@ page language="java" import = "javax.sql.DataSource, it.unisa.gp.model.bean.VideogiocoBean, it.unisa.gp.model.DAO.VideogiocoDS,
	 it.unisa.gp.model.interfaceDS.Videogioco,
	 java.util.* "

 	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%
	DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
	Videogioco vidDS = new VideogiocoDS(ds);

	List<VideogiocoBean> colVid = (List<VideogiocoBean>) vidDS.doRetrieveAll(null);
	Random rand = new Random();
	int indice = rand.nextInt(colVid.size());
	VideogiocoBean vidBean = vidDS.doRetrieveByKey(colVid.get(indice).getCodice());
%>
    
<!DOCTYPE html>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Homepage</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="icon" type="image/png" sizes="32x32" href="img/icon/favicon.png">
<link href="style/style.css" rel="stylesheet">
<link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css" rel="stylesheet">
</head>

<body>
	<script src="script/jquery-3.6.0.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

	<%@ include file="../fragments/header.jsp"%>
	<div class="container">
		<div class="row text-center">
			<h3 class="my-3">Abbonamenti pi&ugrave; acquistati</h3>
			
			<div class="col-md-4 my-3">
				<div class="card text-center w-100">
					<a href="prodottoAbb.jsp?id=Silver Pass"> <img class="card-img-top" src="ImageServlet?immagine=Silver Pass.jpg" alt="Card image"> </a>				
				</div>
			</div>
				
			<div class="col-md-4 my-3">
				<div class="card text-center w-100">
					<a href="prodottoAbb.jsp?id=Gold Pass"> <img class="card-img-top" src="ImageServlet?immagine=Gold Pass.jpg" alt="Card image"> </a>
				</div>
			</div>
			
			<div class="col-md-4 my-3">
				<div class="card text-center w-100">
					<a href="prodottoAbb.jsp?id=Platinum Pass"> <img class="card-img-top" src="ImageServlet?immagine=Platinum Pass.jpg" alt="Card image"> </a>
				</div>
			</div>
			
		</div>
		<br/>
		<div class="row text-center">
			<a href="catalogoAbb.jsp"> <button class="btn btn-primary my-3" > Visualizza tutti gli abbonamenti </button> </a>
		</div>	
		
		<hr>

		<div class="row text-center">
			<h3 class="my-3">Scegliamo un gioco per te</h3>
			
			<div class="col-md-4 my-3 mx-auto ">
				<div class="card text-center w-100">
					<a href="prodottoVideog.jsp?id=<%=vidBean.getCodice()%>"> <img class="card-img-top image-responsive" src="ImageServlet?immagine=<%=vidBean.getCodice()%>_1.jpg" alt="Card image"> </a>				
				</div>
			</div>	
		</div>
		
		<hr>
		
		<div class="row text-center">
			<h3 class="my-3">Ultimi arrivi</h3>
			
			<div class="col-md-3 my-3">
				<div class="card text-center w-100">
					<a href="prodottoVideog.jsp?id=1PLO32Q6"> <img class="card-img-top" src="ImageServlet?immagine=1PLO32Q6_1.jpg" alt="Card image"> </a>				
				</div>
			</div>

			<div class="col-md-3 my-3">
				<div class="card text-center w-100">
					<a href="prodottoVideog.jsp?id=CVWE44P0"> <img class="card-img-top" src="ImageServlet?immagine=CVWE44P0_1.jpg" alt="Card image"> </a>				
				</div>
			</div>
				
			<div class="col-md-3 my-3">
				<div class="card text-center w-100">
					<a href="prodottoVideog.jsp?id=FCR787U0"> <img class="card-img-top" src="ImageServlet?immagine=FCR787U0_1.jpg" alt="Card image"> </a>
				</div>
			</div>
			
			<div class="col-md-3 my-3">
				<div class="card text-center w-100">
					<a href="prodottoVideog.jsp?id=BVNMQ456"> <img class="card-img-top" src="ImageServlet?immagine=BVNMQ456_1.jpg" alt="Card image"> </a>
				</div>
			</div>
		</div>	
		
		<hr> <br/>
		
			<!--Section: FAQ-->
	<section>
	  <h3 class="text-center mb-4 pb-2"> FAQ</h3>
	  <p class="text-center mb-5">
	    Trova le risposte alle domande richieste pi&ugrave; frequentemente mostrate qui sotto.
	  </p>
	
	  <div class="row">
	    <div class="col-md-6 col-lg-4 mb-4">
	      <h6 class="mb-3 text-primary"><i class="far fa-paper-plane text-primary pe-2"></i> Questo sito &egrave; sicuro?</h6>
	      <p>
	        <strong><u>Certamente!</u></strong> Assolutamente! Lavoriamo con le migliori societ&agrave; di pagamento che garantiscono la tua sicurezza. 
	        Tutte le informazioni di fatturazione sono memorizzate sul nostro partner di elaborazione dei pagamenti.
	      </p>
	    </div>
	
	    <div class="col-md-6 col-lg-4 mb-4">
	      <h6 class="mb-3 text-primary"><i class="fas fa-pen-alt text-primary pe-2"></i> Posso ricevere assistenza?</h6>
	      <p>
	        <strong><u>Certo, &egrave; possibile!</u></strong> Tu puoi contattare un operatore con un tickets nella sezione dedicata sulla barra di navigazione, e spiegargli il problema.
	        L'operatore provveder&agrave; tempestivamente a risolverlo.
	      </p>
	    </div>
	
	    <div class="col-md-6 col-lg-4 mb-4">
	      <h6 class="mb-3 text-primary"><i class="fas fa-user text-primary pe-2"></i> Come posso ottenere il Platinum Pass?
	      </h6>
	      <p>
			Acquistalo ora nella sezione Catalogo Abbonamenti > Platinum Pass. Oppure  <a href="prodottoAbb.jsp?id=Platinum Pass">clicca qui</a>.
	      </p>
	    </div>
	  </div>
	</section>
	<!--Section: FAQ-->	
		
		


	</div>
	<%@ include file="../fragments/footerReg.jsp" %>
</body>
</html>