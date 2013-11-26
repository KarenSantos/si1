package models;

import java.util.*;

import play.db.ebean.*;
import play.data.validation.Constraints.*;

import javax.persistence.*;

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
	
	public Task() {
		super();
		this.status = "undone";
	}

	public static Finder<Long, Task> find = 
			new Finder(Long.class, Task.class);

	public static List<Task> all() {
		List<Task> tasks = new ArrayList<Task>(find.all());
		Collections.sort(tasks);
		return tasks;
	}

	public static void create(Task task) {
		task.save();
	}

	public static void delete(Long id) {
		find.ref(id).delete();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getProjeto() {
		return projeto;
	}

	public void setProjeto(String projeto) {
		this.projeto = projeto;
	}

	public int getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(int prioridade) {
		this.prioridade = prioridade;
	}
	
	public String getStatus(){
		return status;
	}
	
	public void setStatus(String status){
		this.status = status;
	}
	
	public static void taskDone(Long id){
		Task task = find.ref(id);
		task.setStatus("done");
		task.update();
	}

	@Override
	public int compareTo(Task task) {
		return getPrioridade() - task.getPrioridade();
	}
}