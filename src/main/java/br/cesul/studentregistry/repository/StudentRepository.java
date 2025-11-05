package br.cesul.studentregistry.repository;

import br.cesul.studentregistry.model.Student;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

// Serve para fazer atualizacoes no mongo
import static com.mongodb.client.model.Updates.set;

public class StudentRepository {

    private final MongoCollection<Student> collection;

    public StudentRepository(MongoDatabase db){
        this(db, "students");
    }
    public StudentRepository(MongoDatabase db, String collectionName){
        this.collection = db.getCollection(collectionName, Student.class);
    }

    // findAll() - retorna todos os estudantes
    public List<Student> findAll(){
        var list = new ArrayList<Student>();

        collection.find().into(list); // find retorna um iteravel e preenchemos com o .into() a lista
        return list;
    }

    public Student save(Student student){
        collection.insertOne(student);
        return student;
    }

    public void toggleFlag(String id){
        var found = collection.find(byId(id)).first();

        if (found != null){
            collection.updateOne(byId(id), set("active", !found.isActive()));
        }
    }

    private Bson byId(String id){
        return Filters.or(
                Filters.eq("_id", id),
                Filters.eq("id", id)
        );
    }

    public void updateField(String id, double newGrade){
        collection.updateOne(byId(id), set("grade", newGrade));
    }

    public void deleteAll(){
        collection.deleteMany(new Document());
    }
}
