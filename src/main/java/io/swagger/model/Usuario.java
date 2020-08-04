package io.swagger.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;

/**
 * Usuario
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-08-03T22:31:34.730Z")

@Entity
@Table(name = "TB_USUARIO")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("id")
	private Long id = null;

	@Column(name = "NOME", length = 200, nullable = false)
	@JsonProperty("nome")
	private String nome = null;

	@Column(name = "USUARIO", length = 15, nullable = false)
	@JsonProperty("usuario")
	private String usuario = null;

	@Column(name = "SENHA", length = 20, nullable = false)
	@JsonProperty("senha")
	private String senha = null;

	@Column(name = "EMAIL", length = 30, nullable = true)
	@JsonProperty("email")
	private String email = null;

	@Column(name = "TELEFONE", length = 9, nullable = true)
	@JsonProperty("telefone")
	private String telefone = null;

	public Usuario() {
	}

	public Usuario(String nome, String usuario, String senha, String email, String telefone) {
		this.setStatus(StatusEnum.Ativo);
		this.setUsuario(usuario);
		this.setEmail(email);
		this.setTelefone(telefone);
		this.setSenha(senha);
		this.setNome(nome);
	}

	/**
	 * Status do usuário
	 */
	public enum StatusEnum {
		Ativo("ATIVO"),

		Inativo("INATIVO");

		private String value;

		StatusEnum(String value) {
			this.value = value;
		}

		@Override
		@JsonValue
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static StatusEnum fromValue(String text) {
			for (StatusEnum b : StatusEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}
	}

	@JsonProperty("status")
	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS", nullable = false)
	private StatusEnum status;

	public Usuario id(Long id) {
		this.id = id;
		return this;
	}

	/**
	 * Get id
	 * 
	 * @return id
	 **/
	@ApiModelProperty(value = "")

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario nome(String nome) {
		this.nome = nome;
		return this;
	}

	/**
	 * Get nome
	 * 
	 * @return nome
	 **/
	@ApiModelProperty(value = "")

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Usuario usuario(String usuario) {
		this.usuario = usuario;
		return this;
	}

	/**
	 * Get usuario
	 * 
	 * @return usuario
	 **/
	@ApiModelProperty(value = "")

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Usuario senha(String senha) {
		this.senha = senha;
		return this;
	}

	/**
	 * Get senha
	 * 
	 * @return senha
	 **/
	@ApiModelProperty(value = "")

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Usuario email(String email) {
		this.email = email;
		return this;
	}

	/**
	 * Get email
	 * 
	 * @return email
	 **/
	@ApiModelProperty(value = "")

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Usuario telefone(String telefone) {
		this.telefone = telefone;
		return this;
	}

	/**
	 * Get telefone
	 * 
	 * @return telefone
	 **/
	@ApiModelProperty(value = "")

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Usuario status(StatusEnum status) {
		this.status = status;
		return this;
	}

	/**
	 * Status do usuário
	 * 
	 * @return status
	 **/
	@ApiModelProperty(value = "Status do usuário")

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Usuario usuario = (Usuario) o;
		return Objects.equals(this.id, usuario.id) && Objects.equals(this.nome, usuario.nome)
				&& Objects.equals(this.usuario, usuario.usuario) && Objects.equals(this.senha, usuario.senha)
				&& Objects.equals(this.email, usuario.email) && Objects.equals(this.telefone, usuario.telefone)
				&& Objects.equals(this.status, usuario.status);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nome, usuario, senha, email, telefone, status);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Usuario {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    nome: ").append(toIndentedString(nome)).append("\n");
		sb.append("    usuario: ").append(toIndentedString(usuario)).append("\n");
		sb.append("    senha: ").append(toIndentedString(senha)).append("\n");
		sb.append("    email: ").append(toIndentedString(email)).append("\n");
		sb.append("    telefone: ").append(toIndentedString(telefone)).append("\n");
		sb.append("    status: ").append(toIndentedString(status)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
