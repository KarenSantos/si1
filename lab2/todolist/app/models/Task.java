package models;

import java.util.*;

import play.db.ebean.*;
import play.data.validation.Constraints.*;

import javax.persistence.*;

/**
 * 
 * Classe de tarefas.
 * 
 */
@Entity
public class Task extends Model implements Comparable<Task> {

	@Id
	private Long id;

	@Required
	private String label;
	private String descricao;
	private String projeto;
	private int prioridade;
	private String status;

	/**
	 * Contrutor que cria uma tarefa com o status undone.
	 */
	public Task() {
		super();
		this.status = "undone";
	}

	public static Finder<Long, Task> find = new Finder(Long.class, Task.class);

	/**
	 * Método que lista todas as tarefas armazenadas.
	 * 
	 * @return A lista de tarefas armazenadas.
	 */
	public static List<Task> all() {
		List<Task> tasks = new ArrayList<Task>(find.all());
		Collections.sort(tasks);
		return tasks;
	}

	/**
	 * Método que salva uma tarefa no banco de dados.
	 * 
	 * @param task
	 *            Tarefa que vai ser armazenada.
	 */
	public static void create(Task task) {
		task.save();
	}

	/**
	 * Método que deleta uma tarefa do banco de dados através de um
	 * identificador.
	 * 
	 * @param id
	 *            O identificador da tarefa a ser deletada.
	 */
	public static void delete(Long id) {
		find.ref(id).delete();
	}

	/**
	 * Recupera o identificador de uma tarefa.
	 * 
	 * @return O identificador da tarefa.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Modifica o idendificador de uma tarefa.
	 * 
	 * @param id
	 *            O novo identificador da tarefa.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Recupera o nome de uma tarefa.
	 * 
	 * @return O nome da tarefa.
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Modifica o nome de uma tarefa.
	 * 
	 * @param label
	 *            O novo nome da tarefa.
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * Recupera a descrição de uma tarefa.
	 * 
	 * @return A descrição da tarefa.
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Modifica a descrição de uma tarefa.
	 * 
	 * @param descricao
	 *            A nova descrição da tarefa.
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * Recupera o projeto a qual a tarefa está associada.
	 * 
	 * @return O projeto a qual a tarefa está associada.
	 */
	public String getProjeto() {
		return projeto;
	}

	/**
	 * Modifica o projeto a qual a tarefa está associada.
	 * 
	 * @param projeto
	 *            O novo projeto a qual a tarefa está associada.
	 */
	public void setProjeto(String projeto) {
		this.projeto = projeto;
	}

	/**
	 * Recupera a prioridade de uma tarefa.
	 * 
	 * @return A prioridade da tarefa.
	 */
	public int getPrioridade() {
		return prioridade;
	}

	/**
	 * Modifica a prioridade de uma tarefa.
	 * 
	 * @param prioridade
	 *            A nova prioridade da tarefa.
	 */
	public void setPrioridade(int prioridade) {
		this.prioridade = prioridade;
	}

	/**
	 * Recupera o status de uma tarefa.
	 * 
	 * @return O status de uma tarefa.
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Modifica o status de uma tarefa.
	 * 
	 * @param status
	 *            O novo status da tarefa.
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Marca a tarefa como feita.
	 * 
	 * @param id
	 *            O identificador da tarefa a ser marcada feita.
	 */
	public static void taskDone(Long id) {
		Task task = find.ref(id);
		task.setStatus("done");
		task.update();
	}

	/**
	 * Retorna a quantidade de tarefas armazenadas de acordo com o seu status.
	 * 
	 * @param status
	 *            O status das tarefas a serem contadas.
	 * @return A quantidade de tarefas do respectivo status.
	 */
	public static Integer tasksByStatus(String status) {
		int resp = 0;
		for (Task task : all()) {
			if (task.getStatus().equals(status)) {
				resp++;
			}
		}
		return (Integer) resp;
	}

	/**
	 * Compara as tarefas de acordo com a sua prioridade.
	 */
	@Override
	public int compareTo(Task task) {
		return getPrioridade() - task.getPrioridade();
	}
}