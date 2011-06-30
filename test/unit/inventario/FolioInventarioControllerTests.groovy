package inventario



import org.junit.*
import grails.test.mixin.*


@TestFor(FolioInventarioController)
@Mock(FolioInventario)
class FolioInventarioControllerTests {


    @Test
    void testIndex() {
        controller.index()
        assert "/folioInventario/list" == response.redirectedUrl
    }

    @Test
    void testList() {

        def model = controller.list()

        assert model.folioInventarioInstanceList.size() == 0
        assert model.folioInventarioInstanceTotal == 0

    }

    @Test
    void testCreate() {
       def model = controller.create()

       assert model.folioInventarioInstance != null


    }

    @Test
    void testSave() {
        controller.save()

        assert model.folioInventarioInstance != null
        assert view == '/folioInventario/create'

        // TODO: Populate valid properties

        controller.save()

        assert response.redirectedUrl == '/folioInventario/show/1'
        assert controller.flash.message != null
        assert FolioInventario.count() == 1
    }


    @Test
    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/folioInventario/list'


        def folioInventario = new FolioInventario()

        // TODO: populate domain properties

        assert folioInventario.save() != null

        params.id = folioInventario.id

        def model = controller.show()

        assert model.folioInventarioInstance == folioInventario
    }

    @Test
    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/folioInventario/list'


        def folioInventario = new FolioInventario()

        // TODO: populate valid domain properties

        assert folioInventario.save() != null

        params.id = folioInventario.id

        def model = controller.edit()

        assert model.folioInventarioInstance == folioInventario
    }

    @Test
    void testUpdate() {

        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/folioInventario/list'

        response.reset()


        def folioInventario = new FolioInventario()

        // TODO: populate valid domain properties

        assert folioInventario.save() != null

        // test invalid parameters in update
        params.id = folioInventario.id

        controller.update()

        assert view == "/folioInventario/edit"
        assert model.folioInventarioInstance != null

        folioInventario.clearErrors()

        // TODO: populate valid domain form parameter
        controller.update()

        assert response.redirectedUrl == "/folioInventario/show/$folioInventario.id"
        assert flash.message != null
    }

    @Test
    void testDelete() {
        controller.delete()

        assert flash.message != null
        assert response.redirectedUrl == '/folioInventario/list'

        response.reset()

        def folioInventario = new FolioInventario()

        // TODO: populate valid domain properties
        assert folioInventario.save() != null
        assert FolioInventario.count() == 1

        params.id = folioInventario.id

        controller.delete()

        assert FolioInventario.count() == 0
        assert FolioInventario.get(folioInventario.id) == null
        assert response.redirectedUrl == '/folioInventario/list'


    }


}