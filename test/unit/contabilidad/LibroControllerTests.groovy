package contabilidad



import org.junit.*
import grails.test.mixin.*


@TestFor(LibroController)
@Mock(Libro)
class LibroControllerTests {


    @Test
    void testIndex() {
        controller.index()
        assert "/libro/list" == response.redirectedUrl
    }

    @Test
    void testList() {

        def model = controller.list()

        assert model.libroInstanceList.size() == 0
        assert model.libroInstanceTotal == 0

    }

    @Test
    void testCreate() {
       def model = controller.create()

       assert model.libroInstance != null


    }

    @Test
    void testSave() {
        controller.save()

        assert model.libroInstance != null
        assert view == '/libro/create'

        // TODO: Populate valid properties

        controller.save()

        assert response.redirectedUrl == '/libro/show/1'
        assert controller.flash.message != null
        assert Libro.count() == 1
    }


    @Test
    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/libro/list'


        def libro = new Libro()

        // TODO: populate domain properties

        assert libro.save() != null

        params.id = libro.id

        def model = controller.show()

        assert model.libroInstance == libro
    }

    @Test
    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/libro/list'


        def libro = new Libro()

        // TODO: populate valid domain properties

        assert libro.save() != null

        params.id = libro.id

        def model = controller.edit()

        assert model.libroInstance == libro
    }

    @Test
    void testUpdate() {

        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/libro/list'

        response.reset()


        def libro = new Libro()

        // TODO: populate valid domain properties

        assert libro.save() != null

        // test invalid parameters in update
        params.id = libro.id

        controller.update()

        assert view == "/libro/edit"
        assert model.libroInstance != null

        libro.clearErrors()

        // TODO: populate valid domain form parameter
        controller.update()

        assert response.redirectedUrl == "/libro/show/$libro.id"
        assert flash.message != null
    }

    @Test
    void testDelete() {
        controller.delete()

        assert flash.message != null
        assert response.redirectedUrl == '/libro/list'

        response.reset()

        def libro = new Libro()

        // TODO: populate valid domain properties
        assert libro.save() != null
        assert Libro.count() == 1

        params.id = libro.id

        controller.delete()

        assert Libro.count() == 0
        assert Libro.get(libro.id) == null
        assert response.redirectedUrl == '/libro/list'


    }


}