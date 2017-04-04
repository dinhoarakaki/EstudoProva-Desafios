package usuController;

import java.io.IOException;

import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helper.JsonHelper;
import produto.Produto;

@WebServlet(urlPatterns="/usucontroller")
public class UsuarioController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private List<Object> lista = new ArrayList<>();
	
	private JsonHelper jsonHelper =  new JsonHelper();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String descricao = req.getParameter("descricao");
		double valor = Double.parseDouble(req.getParameter("valor"));
		int id = lista.size()+1;
		Produto prod = new Produto(id,descricao, valor);
		lista.add(prod);
		
		try {
			resp.getWriter().println(jsonHelper.gerarJson(prod));
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
//--------------------	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String tipo = req.getParameter("tipo");
		String json;
		double total=0;
		try {
			DecimalFormat df = new DecimalFormat("0.##");
			
			if(tipo.equals("soma")){
				for(int x=0; x<lista.size();x++){
					total+=((Produto) lista.get(x)).getValor();
				}
				json = "{Soma:"+df.format(total)+"}";
			}else{
				json = jsonHelper.gerarJsonLista(lista);
			}
			
			resp.getWriter().print(json);
				
		} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//--------------------
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		for(int x=0; x<lista.size();x++){
			if(((Produto) lista.get(x)).getId()==id) 
			lista.remove(x);
		}
	}
//--------------------
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		String descricao = req.getParameter("descricao");
		double valor = Double.parseDouble(req.getParameter("valor"));
		Produto aux=(Produto)lista.get(id-1);
//		for(int x=0; x<lista.size();x++){
//			if(((Produto) lista.get(x)).getId()==id){ 
//			((Produto) lista.get(x)).setDescricao(descricao);
//			((Produto) lista.get(x)).setValor(valor);
//			}
//		}
		
		for(int x=0; x<lista.size();x++){
			if(((Produto) lista.get(x)).getId()==id){
				aux = (Produto) lista.get(x);
				aux.setDescricao(descricao);
				aux.setValor(valor);
			}
		}
		
		try {
			resp.getWriter().println(jsonHelper.gerarJson(aux));
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
